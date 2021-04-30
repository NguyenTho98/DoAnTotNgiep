import React from 'react';
import * as Icons from "../../Icons/Icons";
import "./filterTerms.scss";

function FilterTerms(props) {

  const { setShowFilter } = props;

  return (
    <div id="filter-wrapper">
      <div id="filter-option-wrapper">
        <button type="button" id="filter-button"
        onClick={() => setShowFilter()}
        >
          Lọc khách hàng
          <Icons.ArrowDown />
        </button>
        <div id="filter-search">
          <div id="filter-search-icon">
            <Icons.Search />
          </div>
          <input
            id="filter-search-input"
            placeholder="Tìm kiếm khách hàng"
            value={''}
          />
        </div>
      </div>
    </div>
  );
}

export default FilterTerms;
