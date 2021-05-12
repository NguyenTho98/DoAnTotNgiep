/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import { useHistory } from 'react-router';
import * as Icons from 'pages/maintenancecard/commons/Icons';
import '../../styles/header.scss';
import { connect } from 'react-redux';

function Header(props) {
  const history = useHistory();
  const { filterInfo } = props;
  const onClickCreateMainCard = () => {
    alert("xin cahfo")
  };

  const renderBulkAction = () => {
    const {
      child, onClick, checked, minus
    } = props;

    return (
      <React.Fragment>
        <div className="d-flex dropdown dd-bulk-action main-card-bulk-action-dd">
          <div className="d-flex checkbox-wrapper">
            {
              minus ? (
                <div role="presentation" className="checkbox header-checkbox" onClick={() => onClick()}>
                  <Icons.Minus />
                </div>
              ) : (
                <div role="presentation" className="checkbox header-checkbox" onClick={() => onClick()}>
                  <input
                    type="checkbox"
                    name="check"
                    checked={checked}
                    readOnly
                  />
                  <label />
                </div>
              )
            }
            {child}
          </div>
          <button
            className="btn btn-default dropdown-toggle"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Lựa chọn thao tác
          </button>
          <div className="dropdown-menu bulk-dd-menu" aria-labelledby="dropdownMenuButton">
            <a className="dropdown-item" onClick={() => onClickCreateMainCard()}>Xoá</a>
          </div>
        </div>
      </React.Fragment>
    );
  };
  const { onClick, checked, minus } = props;
  return (
    <div className="d-flex header-list main-card-header" style={{ padding: checked || minus ? '0px 0 8px' : '' }}>
      {
          checked || minus ? null : (
            <div className="checkbox header-checkbox" onClick={() => onClick()}>
              <input
                type="checkbox"
                name="check"
                checked={checked}
                readOnly
              />
              <label />
            </div>
          )
        }
      {
          checked || minus ? (
            renderBulkAction()
          ) : (
            <React.Fragment>
              <div className="">
                &nbsp;&nbsp;
              </div>
              <div className="margin-right20 header-item order-collations-code">
                Mã phiếu
              </div>
              <div className="margin-right20 header-item order-collations-fulfillment">
                Khách hàng
              </div>
              <div className="margin-right20 header-item order-collations-loaction">
                Biển số xe
              </div>
              <div className="margin-right20 header-item order-collations-status">
                NV điều phối
              </div>
              <div className="margin-right20 header-item order-collations-channel-code">
                NV sửa chưa
              </div>
              <div className="margin-right20 header-item order-collations-total-amount">
                TT thanh toán
              </div>
              <div className="margin-right20 header-item order-collations-ship-fee">
                TT công việc
              </div>
              <div className="margin-right20 header-item order-collations-paid">
                Ngày trả xe
              </div>
              <div className="margin-right20 header-item order-collations-paid">
                Giá tiền
              </div>
            </React.Fragment>
          )
        }
    </div>
  );
}
const mapStateToProps = (state) => {
  const { mainCard: { filterInfo, } } = state;
  return {
    filterInfo,
  };
};
export default connect(mapStateToProps, null)(Header);
