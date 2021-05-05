/* eslint-disable no-shadow */
import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import "./styles.scss";
function CustomerDetailInfo(props) {
  const {} = props;
  useEffect(() => {}, []);
  return (
    <div className="customer-detail-info">
      <div className="card">
        <div className="title">Thông tin khách hàng</div>
        <div className="content">
          xin chào
        </div>
      </div>

    </div>
  );
}
CustomerDetailInfo.defaultProps = {};

export default React.memo(connect(null, null)(CustomerDetailInfo));
