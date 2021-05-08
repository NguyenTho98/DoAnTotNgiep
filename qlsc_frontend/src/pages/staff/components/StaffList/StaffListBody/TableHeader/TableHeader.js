import React from "react";
import { connect } from "react-redux";

import FilterStaff from "./components/FilterStaff";

function TableHeader(props) {
  const { search, handleInputOnchange } = props;
  return (
    <div id="delivery-collations-table-header" style={{ position: "relative" }}>
      <FilterStaff search={search} handleInputOnchange={handleInputOnchange} />
    </div>
  );
}

const mapStateToProps = (state) => {
  //
};

export default connect(mapStateToProps, null)(TableHeader);
