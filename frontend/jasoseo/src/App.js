import "./App.css";

import { Route, Routes } from "react-router-dom";

import Main from "./pages/main";
import Writer from "./pages/writer";

function App() {
  return (
    <Routes>
      <Route index element={<Main />} />
      <Route path="/writer" element={<Writer />} />
    </Routes>
  );
}

export default App;
