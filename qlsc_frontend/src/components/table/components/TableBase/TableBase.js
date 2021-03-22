import React from 'react';
import FilterBase from "../FilterBase/FilterBase";
import FilterTerms from "../FilterTerms/FilterTerms";
import "./tableBase.scss";
import FilterTermsModal from "../FilterTerms/FilterTermsModal/FilterTermsModal";

function TableBase (props) {
  return (
    <React.Fragment>
        <div id="wrapper-table">
            <FilterBase />
            <FilterTerms />
            <FilterTermsModal />
        </div>
    </React.Fragment>
  )
}

export default TableBase
