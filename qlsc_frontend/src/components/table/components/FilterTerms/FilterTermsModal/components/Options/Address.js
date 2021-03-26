import React from "react";

function Address(props) {
  const { filterAddress, filterAction } = props;
  return (
    <span className="filter-detail">
      <span className="active-status">
        {filterAddress.hasAddress && filterAddress.status ? (
          <button
            className="btn-active"
            onClick={() => filterAction(option, true)}
            style={{ background: "#4e95e3", color: "white" }}
            type="button"
          >
            Có địa chỉ
          </button>
        ) : (
          <button
            className="btn-active"
            onClick={() => filterAction(option, true)}
            type="button"
          >
            Có địa chỉ
          </button>
        )}
      </span>
      <span className="active-status">
        {!filterAddress.hasAddress && filterAddress.status ? (
          <button
            className="btn-unActive"
            onClick={() => filterAction(option, false)}
            style={{ background: "#4e95e3", color: "white" }}
            type="button"
          >
            Không có địa chỉ
          </button>
        ) : (
          <button
            className="btn-unActive"
            onClick={() => filterAction(option, false)}
            type="button"
          >
            Không có địa chỉ
          </button>
        )}
      </span>
    </span>
  );
}

export default Address;
