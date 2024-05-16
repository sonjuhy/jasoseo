import * as React from "react";
import PropTypes from "prop-types";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import Drawer from "@mui/material/Drawer";
import IconButton from "@mui/material/IconButton";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import FormatAlignLeftIcon from "@mui/icons-material/FormatAlignLeft";
import MenuIcon from "@mui/icons-material/Menu";
import Toolbar from "@mui/material/Toolbar";

import { Button, Typography, Divider } from "@mui/material";
import InputModule from "../modules/inputModule";

const drawerWidth = 240;

function ResponsiveDrawer(props) {
  const { window } = props;
  const [mobileOpen, setMobileOpen] = React.useState(false);
  const [isClosing, setIsClosing] = React.useState(false);

  const [resumeName, setResumeName] = React.useState("Resume Name");

  const sideBarList = [
    ["Company 1", "Back-End 분야", "2024.04.01", "2024.04.18"],
    ["Company 2", "Front-End 분야", "2024.04.05", "2024.04.22"],
    ["Company 3", "회계 파트", "2024.04.05", "2024.05.01"],
    ["Company 4", "서버 엔지니어링", "2024.04.15", "2024.05.11"],
  ];

  const handleDrawerClose = () => {
    setIsClosing(true);
    setMobileOpen(false);
  };

  const handleDrawerTransitionEnd = () => {
    setIsClosing(false);
  };

  const handleDrawerToggle = () => {
    if (!isClosing) {
      setMobileOpen(!mobileOpen);
    }
  };

  const handleAddButton = () => {};

  const drawer = (
    <div>
      <Toolbar sx={{ boxShadow: 3 }}>
        <FormatAlignLeftIcon
          color={"secondary"}
          style={{ marginRight: "1rem" }}
        />
        <Typography variant="overline" color={"blueviolet"} fontSize={18}>
          resume list
        </Typography>
      </Toolbar>
      <Divider />
      <List>
        {sideBarList.map((info, index) => (
          <Box key={info[0]}>
            <ListItem disablePadding>
              <ListItemButton>
                <Box>
                  <ListItemText primary={info[0]} secondary={info[1]} />
                  <ListItemText secondary={info[2]} />
                  <ListItemText secondary={info[3]} />
                </Box>
              </ListItemButton>
            </ListItem>
            <Divider />
          </Box>
        ))}
      </List>
    </div>
  );

  // Remove this const when copying and pasting into your project.
  const container =
    window !== undefined ? () => window().document.body : undefined;

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
        }}
        color="transparent"
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: "none" } }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="overline" fontSize={18} noWrap component="div">
            Write Resume
          </Typography>
        </Toolbar>
      </AppBar>
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
        <Drawer
          container={container}
          variant="temporary"
          open={mobileOpen}
          onTransitionEnd={handleDrawerTransitionEnd}
          onClose={handleDrawerClose}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: "block", sm: "none" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: "none", sm: "block" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          width: { sm: `calc(100% - ${drawerWidth}px)` },
        }}
      >
        <Toolbar />
        <Box>
          <Typography variant="h2" style={{ padding: "3rem" }}>
            {resumeName}
          </Typography>
          <Divider variant="middle" />
          <Button onClick={handleAddButton}>ADD</Button>
          <InputModule />
        </Box>
      </Box>
    </Box>
  );
}

ResponsiveDrawer.propTypes = {
  /**
   * Injected by the documentation to work in an iframe.
   * Remove this when copying and pasting into your project.
   */
  window: PropTypes.func,
};

export default ResponsiveDrawer;
