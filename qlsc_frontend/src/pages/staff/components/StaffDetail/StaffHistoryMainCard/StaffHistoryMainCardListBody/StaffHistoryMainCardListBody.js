import React from "react";
import "./styles.scss";
import Wrapper from "./TableBody/components/Wrapper/Wrapper";
import TableHeader from "./TableHeader/TableHeader";

function StaffHistoryMainCardListBody(props) {
  const { onGetCustomer, handleInputOnchange, search, onChangeFilter, filter } = props;
  return (
    <div className="customer-list-body">
      <TableHeader search={search} handleInputOnchange={handleInputOnchange} />
      <div className="dashboard-body-content">
        <div className="content-container">
          <Wrapper
            onGetCustomer={onGetCustomer}
            onChangeFilter={onChangeFilter}
            filter={filter}
          />
        </div>
      </div>
    </div>
  );
}
export default StaffHistoryMainCardListBody;
