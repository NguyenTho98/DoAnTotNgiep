/* eslint-disable jsx-a11y/click-events-have-key-events */
import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import '../../styles/item.scss';
import ReactTooltip from 'react-tooltip';
import { moneyFormat } from 'utils/moneyFormat';
import * as Icons from 'pages/maintenancecard/commons/Icons';


function Item(props) {
  const { checked } = props;
  const onCheck = (e) => {
    e.stopPropagation();
    const { onCheckBoxClick } = props;
    onCheckBoxClick(id);
  };


  return (
    <div className="staff-item-wrapper">
      <div
        className="d-flex staff-listing-item"
      >
        <div role="presentation" className="checkbox header-checkbox" onClick={(e) => onCheck(e)}>
          <input
            type="checkbox"
            name="check"
            checked={checked}
            readOnly
          />
          <label />
        </div>
        <div className="">
          &nbsp;&nbsp;
        </div>
        <div className="margin-right20 item-list text-ellipsis">
          <span className="item-name">
            <a
              data-tip
              data-for={`order_collation_number_id_${1}`}
            //   href={calcHref(connection.channel_type)}
              target="_blank"
              style={{ textDecoration: 'none' }}
            >
              CODE01
              <ReactTooltip
                place="top"
                type="dark"
                effect="solid"
                isMultiline
                id={`order_collation_number_id_${1}`}
              >
                CODE01
              </ReactTooltip>

            </a>
          </span>
        </div>
        <div className="margin-right20 item-list text-ellipsis">
            Nguyễn Thọ
        </div>
        <div className="margin-right20 delivery-collation-location"
          data-tip
          data-for={`delivery-collation-location_${1}`}
        >
            nxtho0109@gmail.com
        </div>
        <div className="margin-right20 item-list" style={{ color: status.color }}>
            0357004230
        </div>
        <div className="margin-right20 item-list delivery-collation-code">
            Nhân viên sữa chưa
        </div>
        <div className="margin-right20 item-list order-collations-total-amount">
            4
        </div>
      </div>
    </div>
  );
}

Item.defaultProps = {
  item: {},
};

export default connect(null, null)(Item);
