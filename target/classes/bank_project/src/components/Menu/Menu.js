import { ReactSVG } from "react-svg";

import React, { Component, useEffect, useState } from "react";
import { isReload } from "../../Redux/Reducer/AppReducer/appActions";
import { useDispatch, useSelector } from "react-redux";
import Button from "../Button/Button";
import Modal from "../Modal/Modal";
const Menu = () => {
  const { reload } = useSelector((state) => ({ reload: state.isReload }));
  const dispatch = useDispatch();

  return (
    <header className="head">
      <div className="head-list">
        <div className="block-logo">
          <ReactSVG className="img-logo" src="./phones/smartphone_79223.svg" />

          <h3 className="logo">Zhukov Bank is a new generation bank!</h3>
        </div>
        <Button
          onClick={() => {
            localStorage.removeItem("token");
            dispatch(isReload(!reload));
          }}
        >
          Выход
        </Button>
      </div>
    </header>
  );
};

export default Menu;
