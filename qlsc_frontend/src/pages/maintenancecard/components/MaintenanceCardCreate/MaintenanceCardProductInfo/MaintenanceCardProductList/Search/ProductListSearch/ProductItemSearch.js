/* eslint-disable jsx-a11y/click-events-have-key-events */
import React from 'react';
import { connect } from 'react-redux';
import ReactTooltip from 'react-tooltip';
import {
  TIKI, SENDO, SHOPEE, LAZADA, CHANNEL_LOGO
} from 'common/channelTypeConstants';
import Tooltip from 'components/general/ToolTip/TextWithToolTip';
import { moneyFormat } from 'util/moneyFormat';

function OrderItemSearch(props) {
  const { item, onClick } = props;
  const {
    connection_id,
    channel_order_number,
    sapo_tracking_code,
  } = item;
  const tenant = JSON.parse(sessionStorage.getItem('tenant')) || {};
  const { connections } = tenant || [];
  const connection = connections.find((it) => it.id === connection_id) || {};
  const totalAmount = item.service_provider_cod_amount ? moneyFormat(item.service_provider_cod_amount) : 0;
  const totalShipFee = item.service_provider_ship_fee ? moneyFormat(item.service_provider_ship_fee) : 0;
  return (
    <div className="d-flex align-items-center order-item" onMouseDown={() => onClick(item)}>
      <div className="order-code text-ellipsis">
        <span className="item-name">
          {channel_order_number || '---'}
        </span>
      </div>
      <div className="tracking-code text-ellipsis">
        {sapo_tracking_code || '---'}
      </div>
      <div className="tenant">
        {connection.short_name}
      </div>
      <div className="order-total-amount">
        {totalAmount}
      </div>
      <div className="order-ship-fee">
        {totalShipFee}
      </div>
    </div>
  );
}


export default connect(null, null)(OrderItemSearch);
