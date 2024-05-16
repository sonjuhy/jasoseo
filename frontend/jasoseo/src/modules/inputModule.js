import React, { useEffect, useState } from "react";
import {
  Button,
  Checkbox,
  Box,
  Stack,
  Container,
  TextField,
  Typography,
  Snackbar,
  Divider,
  Paper,
} from "@mui/material";
import CircleIcon from "@mui/icons-material/Circle";
import CreateIcon from "@mui/icons-material/Create";
import CreateOutlinedIcon from "@mui/icons-material/CreateOutlined";
import { red, green, blue, deepPurple } from "@mui/material/colors";
import axios from "axios";

const WriterMain = () => {
  const [title, setTitle] = useState("Title");
  const [fixTitle, setFixTitle] = useState(false);
  const [text, setText] = useState("");
  const [textLen, setTextLen] = useState(0);
  const [fixedText, setFixedText] = useState("");

  const [snackBarText, setSnackBarText] = useState("");
  const [open, setOpen] = useState(false);

  const handleClick = () => {
    setOpen(true);
  };

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpen(false);
  };

  const changeText = (data) => {
    setText(data);
  };

  const copyText = () => {
    if (text.length > 0) {
      setSnackBarText("Copy Success.");
      window.navigator.clipboard.writeText(text);
      handleClick();
    } else {
      setSnackBarText("Write Content.");
      handleClick();
    }
  };

  const spellCheck = () => {
    if (text.length > 0) {
      setFixedText("");
      const result = Checking(text);
      console.log(result);
    } else {
      setSnackBarText("Write Content.");
      handleClick();
    }
  };

  const Checking = async (msg) => {
    axios
      .post("http://127.0.0.1:8000/check", { text: msg })
      .then((response) => {
        console.log(response);
        const dataArray = Object.entries(response.data[4]).map(
          ([key, value]) => ({
            word: key,
            count: value,
          })
        );
        const result = combineStringsAndColors(dataArray);
        setFixedText(result);
      })
      .catch((error) => {
        if (error.response || error.request) {
          setSnackBarText("Error occurred while conn server.");
          handleClick();
        } else {
          console.log("Error : " + error.message);
        }
      });
  };

  const combineStringsAndColors = (arr) => {
    let result = "";

    for (let i = 0; i < arr.length; i++) {
      const color = getColorFromCode(arr[i]["count"]);
      const word = `<span style="color: ${color};">${arr[i]["word"]}</span>`;
      result += word;
      if (i < arr.length - 1) {
        result += " ";
      }
    }

    return result;
  };
  function getColorFromCode(code) {
    switch (code) {
      case 1:
        return "red";
      case 3:
        return "purple";
      case 2:
        return "green";
      case 4:
        return "skyblue";
      default:
        return "black"; // 기본값은 검은색으로 설정
    }
  }

  useEffect(() => {
    const len = text.length;
    setTextLen(len);
  }, [text]);

  useEffect(() => {}, [title]);

  return (
    <Box sx={{ justifyContent: "center", display: "flex" }}>
      <Box sx={{ width: "90%" }}>
        <Snackbar
          open={open}
          autoHideDuration={1500}
          onClose={handleClose}
          message={snackBarText}
        />
        <Box sx={{ margin: "1rem" }}>
          <Stack direction={"row"}>
            {fixTitle ? (
              <>
                <TextField
                  variant="outlined"
                  label={title}
                  multiline
                  size="medium"
                  fullWidth
                  helperText="Title field"
                  onChange={(e) => setTitle(e.target.value)}
                />
              </>
            ) : (
              <>
                <Typography variant="h3">{title}</Typography>
              </>
            )}
            <Checkbox
              icon={<CreateOutlinedIcon />}
              checkedIcon={<CreateIcon />}
              label="Fix"
              checked={fixTitle}
              onChange={(e) => {
                setFixTitle(e.target.checked);
              }}
            />
          </Stack>

          <br />
          <Stack direction={"row"} sx={{ justifyContent: "space-between" }}>
            <Typography variant="overline" fontSize={18}>
              ▪ Content
            </Typography>
            <Box>
              <Button
                onClick={copyText}
                variant="contained"
                style={{ marginRight: "1rem" }}
              >
                Copy
              </Button>
              <Button
                onClick={spellCheck}
                variant="contained"
                color="secondary"
              >
                Check
              </Button>
            </Box>
          </Stack>

          <TextField
            variant="outlined"
            label="content"
            multiline
            size="medium"
            fullWidth
            helperText="content field"
            onChange={(e) => changeText(e.target.value)}
            minRows={7}
            sx={{ borderRadius: "15px" }}
          />

          <Box sx={{ marginTop: "1rem", marginBottom: "1rem" }}>
            <Typography>
              입력된 문자 길이 :{" "}
              {textLen === 0 ? "입력 없음." : textLen + " 자"}
            </Typography>
          </Box>
          <Divider variant="middle" />
          <br />

          <Typography variant="overline" fontSize={20}>
            ▪ 맞춤법 검사 결과
          </Typography>
          <Box
            sx={{
              border: "1px solid grey",
              borderRadius: "15px",
              minHeight: "10vh",
            }}
          >
            <div
              dangerouslySetInnerHTML={{ __html: fixedText }}
              style={{ margin: "1rem" }}
            ></div>
          </Box>
          <Button
            onClick={() => {
              setFixedText("");
            }}
          >
            Clear
          </Button>
          <Stack
            direction={"row"}
            justifyContent="space-between"
            spacing={2}
            margin={"1rem"}
          >
            <Box display={"flex"} flexDirection={"row"}>
              <CircleIcon sx={{ color: red[700] }} />
              <Typography>맞춤법 오류</Typography>
            </Box>
            <Box display={"flex"} flexDirection={"row"}>
              <CircleIcon sx={{ color: green[700] }} />
              <Typography>띄어쓰기 오류</Typography>
            </Box>
            <Box display={"flex"} flexDirection={"row"}>
              <CircleIcon sx={{ color: deepPurple[700] }} />
              <Typography>표준어 의심</Typography>
            </Box>
            <Box display={"flex"} flexDirection={"row"}>
              <CircleIcon sx={{ color: blue[700] }} />
              <Typography>통계적 교정 필요</Typography>
            </Box>
          </Stack>
        </Box>
      </Box>
    </Box>
  );
};
export default WriterMain;
