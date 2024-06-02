package com.intergrated.jasoseo.api.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intergrated.jasoseo.api.dto.CheckResult;
import com.intergrated.jasoseo.api.dto.SpellCheckDto;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellCheckServiceImpl implements SpellCheckService{

    private static final String BASE_URL = "https://search.naver.com/p/csearch/ocontent/util/SpellerProxy";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";

    @Override
    public SpellCheckDto check(String msg) {
        String key = getPassportKey();
        if (key != null) {
            if (msg.length() > 500) {
                return new SpellCheckDto(false);
            }

            try{
                String url = BASE_URL + "?passportKey=" + key + "&color_blindness=0&q=" + msg;
                HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("referer", "https://search.naver.com/");

                long startTime = System.currentTimeMillis();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();

                JsonObject jsonObject = JsonParser.parseString(content.toString()).getAsJsonObject();
                JsonObject resultData = jsonObject.getAsJsonObject("message").getAsJsonObject("result");

                SpellCheckDto result = new SpellCheckDto(true);
                result.original = msg;
                result.checked = removeTag(resultData.get("html").getAsString());
                result.errors = resultData.get("errata_count").getAsInt();
                result.time = System.currentTimeMillis(); // 시간 측정용으로 필요하다면 수정

                String html = resultData.get("html").getAsString();
                html = html.replace("<em class='green_text'>", "<green>")
                        .replace("<em class='red_text'>", "<red>")
                        .replace("<em class='violet_text'>", "<violet>")
                        .replace("<em class='blue_text'>", "<blue>")
                        .replace("</em>", "<end>");

                String[] items = html.split(" ");
                StringBuilder tmp = new StringBuilder();
                for (String word : items) {
                    if (tmp.isEmpty() && word.startsWith("<")) {
                        int pos = word.indexOf('>') + 1;
                        tmp.append(word, 0, pos);
                    } else if (!tmp.isEmpty()) {
                        word = tmp + word;
                    }

                    if (word.endsWith("<end>")) {
                        word = word.replace("<end>", "");
                        tmp.setLength(0);
                    }

                    CheckResult checkResult = CheckResult.PASSED;
                    if (word.startsWith("<red>")) {
                        checkResult = CheckResult.WRONG_SPELLING;
                        word = word.replace("<red>", "");
                    } else if (word.startsWith("<green>")) {
                        checkResult = CheckResult.WRONG_SPACING;
                        word = word.replace("<green>", "");
                    } else if (word.startsWith("<violet>")) {
                        checkResult = CheckResult.AMBIGUOUS;
                        word = word.replace("<violet>", "");
                    } else if (word.startsWith("<blue>")) {
                        checkResult = CheckResult.STATISTICAL_CORRECTION;
                        word = word.replace("<blue>", "");
                    }
                    result.words.put(word, checkResult);
                }
                return result;
            }
            catch (Exception e){
                return new SpellCheckDto(false);
            }

        } else {
            return new SpellCheckDto(false);
        }
    }

    @Override
    public String getPassportKey() {
        String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=네이버+맞춤법+검사기";


        try{
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            Pattern pattern = Pattern.compile("passportKey=([^&\"}]+)");
            Matcher matcher = pattern.matcher(content.toString());
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return null;
            }
        }
        catch(Exception e){
            return null;
        }

    }

    @Override
    public String removeTag(String msg) {
        try{
            String text = "<content>" + msg + "</content>";
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
            return doc.getDocumentElement().getTextContent();
        }
        catch (Exception e){
            return null;
        }
    }
}
