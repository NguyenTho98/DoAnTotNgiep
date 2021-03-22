import React, { useState } from "react";
import * as Icons from "../../../../Icons/Icons";

function FilterOption(props) {
  const {
    selectedFilter,
    selectedOption,
    selectOption,
    getOptionName,
    changeOption,
  } = props;

  const renderFilter = () => {
    if (selectedFilter[0] === "select") {
      selectedFilter.map((option, index) => {
        console.log("index", index);
        return (
          <a
            className="dropdown-item"
            key={index}
            onClick={() => selectOption(option)}
          >
            {getOptionName(option)}
          </a>
        );
      });
    } else {
      selectedFilter.push(selectedOption);
      selectedFilter.map((option, index) => {
        return (
          <a
            className="dropdown-item"
            key={index}
            onClick={() => changeOption(option, selectedOption)}
          >
            {getOptionName(option)}
          </a>
        );
      });
    }
  };

  return (
    <div className="filter-option">
      {renderFilter()}
      <button type="button" className="dropdown-toggle dropdown-button">
        {getOptionName(selectedOption)}
        <Icons.Arrow />
      </button>
      <div className="dropdown-menu-test">{renderFilter()}</div>
    </div>
  );
}

export default FilterOption;
