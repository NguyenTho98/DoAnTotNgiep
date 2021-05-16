import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import { Link, withRouter } from "react-router-dom";
import * as Icons from "common/icons";
import "./sideBar.scss";
import { changeShowMenuTopBar } from "../../actions/globalUiActions";
import { menuLinkFull } from "utils/router.js";
import sidebarBg from '../../../asset/resource/bg.jpg';
import {
  ProSidebar,
  Menu,
  MenuItem,
  SubMenu,
  SidebarHeader,
  SidebarFooter,
  SidebarContent,
} from "react-pro-sidebar";
import {
  FaTachometerAlt,
  FaGem,
  FaList,
  FaGithub,
  FaRegLaughWink,
  FaHeart,
} from "react-icons/fa";

function SideBar(props) {
  const url = props.history.location.pathname;
  const { showMenu, changeShowMenuTopBar } = props;
  const [collapsed, setCollapsed] = useState(false);
  const [init, setInit] = useState({
    menu: 99,
    submenu: "",
  });
  useEffect(() => {
    const url = props.history.location.pathname;
    const item = menuLinkFull.find((a) => {
      if (a.url.length > 1) {
        if (url.includes(a.url)) {
          return a;
        }
        return "";
      }
    });
    if (item) {
      const subItem =
        item.submenu.length > 0 ? item.submenu.find((a) => a.url === url) : "";
      if (subItem) {
        setInit({ ...init, menu: item.id, submenu: subItem.id });
      }
    }
  }, []);

  const onClickMoreIcon = () => {
    changeShowMenuTopBar(!showMenu);
  };

  const onSetInit = (a, b) => {
    if (a === init.menu) {
      setInit({ ...init, menu: "", submenu: b });
    } else {
      setInit({ ...init, menu: a, submenu: b });
    }
  };

  const handleCollapsedChange = (checked) => {
    setCollapsed(checked);
  };

  return (
    <ProSidebar
      image={sidebarBg}
      className="side-bar"
      collapsed={collapsed}
      toggled
      breakPoint="md"
    >
      <SidebarHeader>
        <div
          style={{
            display: "flex",
            padding: "10px",
            textTransform: "uppercase",
            fontWeight: "bold",
            fontSize: 14,
            letterSpacing: "1px",
            justifyContent: "center",
          }}
        >
          <div className="title-heder" style={collapsed ? {display: 'none'} : {}}>Hello</div>
          <div
            className="icon-header"
            onClick={() => handleCollapsedChange(!collapsed)}
          >
            <Icons.moreIcon />
          </div>
        </div>
      </SidebarHeader>
      <SidebarContent>
      <Menu iconShape="circle">
          <MenuItem
            icon={<FaTachometerAlt />}
            suffix={<span className="badge red">new</span>}
          >
            dashboard
          </MenuItem>
          <MenuItem icon={<FaGem />}>components</MenuItem>
        </Menu>
        <Menu iconShape="circle">
          <SubMenu
            suffix={<span className="badge yellow">3</span>}
            title="withSuffix"
            icon={<FaRegLaughWink />}
          >
            <MenuItem>submenu 1</MenuItem>
            <MenuItem>submenu 2</MenuItem>
            <MenuItem>submenu 3</MenuItem>
          </SubMenu>
          <SubMenu
            prefix={<span className="badge gray">3</span>}
            title="withPrefix"
            icon={<FaHeart />}
          >
            <MenuItem>submenu 1</MenuItem>
            <MenuItem>submenu 2</MenuItem>
            <MenuItem>submenu 3</MenuItem>
          </SubMenu>
          <SubMenu title="multiLevel" icon={<FaList />}>
            <MenuItem>submenu 1 </MenuItem>
            <MenuItem>submenu 2 </MenuItem>
            <SubMenu title={`$submenu 3`}>
              <MenuItem>submenu 3.1 </MenuItem>
              <MenuItem>submenu 3.2 </MenuItem>
              <SubMenu title={`$submenu 3.3`}>
                <MenuItem>submenu 3.3.1 </MenuItem>
                <MenuItem>submenu 3.3.2 </MenuItem>
                <MenuItem>submenu 3.3.3 </MenuItem>
              </SubMenu>
            </SubMenu>
          </SubMenu>
        </Menu>
      </SidebarContent>
      <SidebarFooter style={{ textAlign: 'center' }}>
        <div
          className="sidebar-btn-wrapper"
          style={{
            padding: '10px',
          }}
        >
          <a
            href="https://github.com/azouaoui-med/react-pro-sidebar"
            target="_blank"
            className="sidebar-btn"
            rel="noopener noreferrer"
          >
            <FaGithub />
            <span> viewSource </span>
          </a>
        </div>
      </SidebarFooter>
    </ProSidebar>
  );
}
SideBar.defaultProps = {
  showMenu: "",
  changeShowMenuTopBar: () => {},
};
const mapStateToProps = (state) => {
  const {
    globalUI: { showMenuTopBar },
  } = state;
  const showMenu = showMenuTopBar;
  return {
    showMenu,
  };
};
const mapDispatchToProps = (dispatch) => ({
  changeShowMenuTopBar: () => dispatch(changeShowMenuTopBar()),
});
export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(SideBar)
);
