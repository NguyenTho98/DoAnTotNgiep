import React from 'react';
import Header from './Header/Header';
import List from './List/List';
import Total from './Total/Total';

import './styles.scss';
import Search from './Search/Search';

function DeliveryCollationOrderList(props) {
  const {
    totalAmount,
    totalShipFee,
    channelType,
    setShowModal,
    listOrderCollation,
    locationId,
    total,
    store,
  } = props;
  return (
    <div className="delivery-collation-order-list-wrapper">
      <Search locationId={locationId} store={store} />
      <Header />
      <List />
      <Total
        totalAmount={totalAmount}
        totalShipFee={totalShipFee}
        total={total}
        channelType={channelType}
        listOrderCollation={listOrderCollation}
        setShowModal={(show) => setShowModal(show)}
      />
    </div>
  );
}

export default DeliveryCollationOrderList;
