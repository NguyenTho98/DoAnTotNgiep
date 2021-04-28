import React from 'react';
import DeliveryCollationOrderList from './DeliveryCollationOrderList/DeliveryCollationOrderList';

import './styles.scss';

function DeliveryCollationOrderInfo(props) {
  const {
    channelType,
    setShowModal,
    listOrderCollation,
    locationId,
    totalAmount,
    totalShipFee,
    total,
    store,
  } = props;
  return (
    <div className="delivery-collation-order-info">
      <div className="title">
        Thông tin đơn hàng đối soát
      </div>
      <DeliveryCollationOrderList
        totalAmount={totalAmount}
        totalShipFee={totalShipFee}
        total={total}
        store={store}
        locationId={locationId}
        channelType={channelType}
        listOrderCollation={listOrderCollation}
        setShowModal={(show) => setShowModal(show)}
      />
    </div>
  );
}

export default DeliveryCollationOrderInfo;
