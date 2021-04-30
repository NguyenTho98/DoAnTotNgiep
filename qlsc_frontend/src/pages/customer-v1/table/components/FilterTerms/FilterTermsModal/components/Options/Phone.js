import React from "react";

function Phone(props) {
  const { filterPhone, filterAction, option } = props;
  return (
    <span className="filter-detail">
      <span className="active-status">
        {filterPhone.hasPhone && filterPhone.status ? (
          <button
            className="btn-active"
            onClick={() => filterAction(option, true)}
            style={{ background: "#4e95e3", color: "white" }}
            type="button"
          >
            Có số điện thoại
          </button>
        ) : (
          <button
            className="btn-active"
            onClick={() => filterAction(option, true)}
            type="button"
          >
            Có số điện thoại
          </button>
        )}
      </span>
      <span className="active-status">
        {!filterPhone.hasPhone && filterPhone.status ? (
          <button
            className="btn-unActive"
            onClick={() => filterAction(option, false)}
            style={{ background: "#4e95e3", color: "white" }}
            type="button"
          >
            Không có số điện thoại
          </button>
        ) : (
          <button
            className="btn-unActive"
            onClick={() => filterAction(option, false)}
            type="button"
          >
            Không có số điện thoại
          </button>
        )}
      </span>
    </span>
  );
}

export default Phone;
