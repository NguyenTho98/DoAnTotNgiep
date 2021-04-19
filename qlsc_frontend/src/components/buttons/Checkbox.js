import React from "react";
import "./checkbox.scss";

function Checkbox(props) {
  const { checked } = props;
  const handleClick = (e) => {
    const { onCheck, disableCheck } = this.props;
    e.stopPropagation();
    if (!disableCheck) {
      onCheck();
    }
  };
  return (
    <div
      className="checkbox-base item-checkbox"
      onClick={handleClick}
      onMouseDown={(e) => e.stopPropagation()}
    >
      <input
        type="checkbox"
        className="checkbox-base"
        name="check"
        checked={checked}
        readOnly
      />
      <label className="checkbox-base" />
    </div>
  );
}

export default Checkbox;
