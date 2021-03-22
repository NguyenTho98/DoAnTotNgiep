import React from 'react';
import "./filterQuick.scss";

function FilterQuick(props) {
  return (
    <ul className="nav div_static_filter_quick">
      <li className="btn_filter active" >
        Tất cả bình luận
      </li>
      <li className="btn_filter">
        Bình luận chứa số điện thoại
      </li>
      <li className="btn_filter">
        Bình luận chứa địa chỉ
      </li>
      <li className="btn_get d-flex align-items-center justify-content-around">
        <button className="btn btn-primary" type="button">
          Xem tất cả bình luận trong hội thoại
        </button>
      </li>
    </ul>
  );
}

export default FilterQuick;
