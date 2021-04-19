import React, { useState } from "react";
import FilterBase from "../FilterBase/FilterBase";
import FilterTerms from "../FilterTerms/FilterTerms";
import "./tableBase.scss";
import FilterTermsModal from "../FilterTerms/FilterTermsModal/FilterTermsModal";
import ContentTable from "../ContentTable/ContentTable";
import PaginationBase from "../Pagination/PaginationBase";

function TableBase(props) {
  const { customer } = props;
  const [showFilter, setShowFilter] = useState(false);

  const handleClick = () => {
    setShowFilter(!showFilter);
  };

  return (
    <React.Fragment>
      <div id="wrapper-table">
        <FilterBase />
        <FilterTerms setShowFilter={handleClick} />
        {showFilter ? <FilterTermsModal /> : null}
        <ContentTable customer={customer} />
        <PaginationBase customer={customer} />
      </div>
    </React.Fragment>
  );
}

export default TableBase;
