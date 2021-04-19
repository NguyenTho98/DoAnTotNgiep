import React, { useState } from "react";
import "./filterQuick.scss";

function FilterQuick() {
  const [filterOption, setFilterOption] = useState('all');

  return (
    <ul className="nav div_static_filter_quick">
      <li
        className={`btn_filter ${filterOption === "all" ? "active" : ""}`}
        onClick={() => setFilterOption("all")}
      >
        Tất cả khách hàng
      </li>
      <li
        className={`btn_filter ${filterOption === "phone" ? "active" : ""}`}
        onClick={() => setFilterOption("phone")}
      >
        Khách hàng chứa số điện thoại
      </li>
      <li
        className={`btn_filter ${filterOption === "address" ? "active" : ""}`}
        onClick={() => setFilterOption("address")}
      >
        Khách hàng chứa địa chỉ
      </li>
      <li className="btn_get d-flex align-items-center justify-content-around">
        <button className="btn btn-primary" type="button">
          Xem tất cả khách hàng
        </button>
      </li>
    </ul>
  );
}

export default FilterQuick;
