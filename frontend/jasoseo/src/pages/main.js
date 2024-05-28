import "../App.css";
import "./main.css";
import { useState, useEffect } from "react";

import { Button, Stack, TextField, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { styled } from "@mui/material/styles";
import { ThemeProvider, createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#ffffff", // 흰색을 primary 색상으로 설정
    },
  },
});
const CustomTextField = styled(TextField)(({ theme }) => ({
  "& .MuiInputBase-root": {
    backgroundColor: "rgba(255, 255, 255, 0.2)", // 반투명한 흰색 배경
    borderRadius: "25px",
  },
}));
function App() {
  const navigator = useNavigate();

  const clickLoginButton = () => {
    // login sequence
    navigator("/writer");
  };
  // useEffect(() => {
  //   fetch("/hi")
  //     .then((res) => {
  //       return res.json();
  //     })
  //     .then(function (result) {
  //       setData(result);
  //     });
  // }, []);
  return (
    <div className="main_bg">
      <ThemeProvider theme={theme}>
        <Stack spacing={4} sx={{ width: "20vw" }}>
          <Typography
            variant="overline"
            textAlign={"center"}
            color={"white"}
            fontSize={32}
          >
            user login
          </Typography>
          <CustomTextField
            label="ID"
            color="primary"
            InputProps={{ style: { color: "white" } }}
            InputLabelProps={{ style: { color: "white" } }}
          />
          <CustomTextField
            label="Password"
            type="password"
            color="primary"
            InputProps={{ style: { color: "white" } }}
            InputLabelProps={{ style: { color: "white" } }}
          />
          <Button
            variant="contained"
            sx={{
              margin: "1rem",
              borderRadius: "25px",
              background: "white",
              color: "black",
            }}
            onClick={clickLoginButton}
          >
            <Typography variant="overline">login</Typography>
          </Button>
        </Stack>
      </ThemeProvider>
    </div>
  );
}

export default App;
