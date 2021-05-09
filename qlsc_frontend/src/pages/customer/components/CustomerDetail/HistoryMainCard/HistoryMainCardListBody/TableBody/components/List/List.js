
import React, { forwardRef, useImperativeHandle } from 'react';
import "../../styles/list.scss";
import Item from "../Item/Item";

import Guard from 'components/Guard/Guard'
import { normalize, schema } from 'normalizr';
const customersTemp = new schema.Entity('items');

const List = forwardRef((props, ref) => {
  const { historyMainCard, fetching, isEmpty, selectedIds, onCheckBoxClick } = props;
  const { customers } = historyMainCard;

  useImperativeHandle(ref, () => ({
    onCheckAll() {
      const { onCheckBoxListClick, selectedIds } = props;
      if (customers.length !== selectedIds.length) {
        const normalized = normalize(customers, [customersTemp]);
        const itemIds = normalized.result;
        onCheckBoxListClick(itemIds);
      } else {
        onCheckBoxListClick([]);
      }
    },
  }));

  if (isEmpty) return '';
  if (fetching) {
    return (
      <Guard />
    );
  }
  return (
    <div className="order-list-container">
      {customers.map((historyMainCard) => {
        return <Item
              key={historyMainCard.code}
              index={historyMainCard.code}
              historyMainCard={historyMainCard}
              checked={selectedIds.includes(historyMainCard.id)}
              onCheckBoxClick={onCheckBoxClick}
          />;
      })}
    </div>
  );
});
List.defaultProps = {
  customers: [],
};
export default List;
