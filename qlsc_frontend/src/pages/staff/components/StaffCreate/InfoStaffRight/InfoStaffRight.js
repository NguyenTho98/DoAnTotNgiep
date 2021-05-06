/* eslint-disable no-shadow */
import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import "./styles.scss";
function InfoStaffRight(props) {
  const {} = props;
  useEffect(() => {}, []);
  return (
    <div className="info-staff-right">
      <div className="card info-staff-right-01">
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
InfoStaffRight.defaultProps = {};

export default React.memo(connect(null, null)(InfoStaffRight));
