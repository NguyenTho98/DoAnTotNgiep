/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import './styles.scss';
import { connect } from 'react-redux';
import { useHistory } from 'react-router';
import pushstate from '../../../../../utils/pushstate';

function TitleAndAction(props) {
  const history = useHistory();
  const onClick = () => {
    pushstate(history, `/customers`);
  };
  return (
    <React.Fragment>
      <div style={{ marginLeft: 15 }} className="go-back" onClick={()=>onClick()}>
        <span>
          <svg
            style={{ width: 10, height: 12, marginTop: 6 }}
            width="12"
            height="23"
            viewBox="0 0 12 23"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M11.1985 1.98609L9.49041 0L0 11.1087L9.5 22.2173L11.1985 20.2312L3.39697 11.1087L11.1985 1.98609Z"
              fill="#212B35"
            />
          </svg>
        </span>
        <div style={{ marginTop: 4 }}>
          <span>
            Danh sách&nbsp;
          </span>
          <span>
            khách hàng
          </span>
        </div>
      </div>
    <div className="customer-detail-tilte-action">
      <div className="d-flex list-header">
        <div className="header-title">
          <div style={{ fontSize: '22px', fontWeight: 600, marginLeft: 17 }}>{props.customer.name || '---'}</div>
        </div>
      </div>
    </div>
    </React.Fragment>
  );
}
TitleAndAction.defaultProps = {

};

export default React.memo(TitleAndAction);
