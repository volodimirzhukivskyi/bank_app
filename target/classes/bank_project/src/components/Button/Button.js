import React from "react";

function Button(props) {
  const { className, children, onClick, disabled = false } = props;
  return (
    <button disabled={disabled} className={className} onClick={onClick}>
      {children}
    </button>
  );
}

export default Button;
