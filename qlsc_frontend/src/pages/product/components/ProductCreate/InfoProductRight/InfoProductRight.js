/* eslint-disable no-shadow */
import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import "./styles.scss";
function InfoProductRight(props) {
  const {} = props;
  useEffect(() => {}, []);
  return (
    <div className="info-product-right">
      <div className="card info-product-right-01">
        <div className="title">Thông tin khác</div>
        <div className="content">
          <div className="field form-group">
            <label className="control-label">Ghi chú</label>
            <div className="controls">
              <textarea placeholder="Ghi chú" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
InfoProductRight.defaultProps = {};

export default React.memo(connect(null, null)(InfoProductRight));
