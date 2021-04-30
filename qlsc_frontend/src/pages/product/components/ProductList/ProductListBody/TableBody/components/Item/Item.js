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
    <div className="product-item-wrapper">
      <div
        className="d-flex product-listing-item"
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
              SP001
              <ReactTooltip
                place="top"
                type="dark"
                effect="solid"
                isMultiline
                id={`order_collation_number_id_${1}`}
              >
                SP001
              </ReactTooltip>

            </a>
          </span>
        </div>
        <div className="margin-right20 item-list text-ellipsis">
           dịch vụ 04
        </div>
        <div className="margin-right20 delivery-collation-location"
          data-tip
          data-for={`delivery-collation-location_${1}`}
        >
            Dịch vụ
        </div>
        <div className="margin-right20 item-list" style={{ color: status.color }}>
           19
        </div>
        <div className="margin-right20 item-list delivery-collation-code">
            cái
        </div>
        <div className="margin-right20 item-list order-collations-total-amount">
            100.000đ
        </div>
      </div>
    </div>
  );
}

Item.defaultProps = {
  item: {},
};

export default connect(null, null)(Item);
