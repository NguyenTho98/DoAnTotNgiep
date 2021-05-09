import React, { forwardRef, useImperativeHandle  } from "react";
import "../../styles/list.scss";
import Item from "../Item/Item";
import Guard from 'components/Guard/Guard'
import { normalize, schema } from 'normalizr';

const staffTemp = new schema.Entity('items');

const List = forwardRef((props, ref) => {
  const { staffs, fetching, isEmpty, selectedIds, onCheckBoxClick } = props;

  useImperativeHandle(ref, () => ({
    onCheckAll() {
      const { onCheckBoxListClick, selectedIds } = props;
      if (staffs.length !== selectedIds.length) {
        const normalized = normalize(staffs, [staffTemp]);
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
      {staffs.map((staff) => {
        return <Item
              key={staff.code}
              index={staff.code}
              staff={staff}
              checked={selectedIds.includes(staff.id)}
              onCheckBoxClick={onCheckBoxClick}
          />;
      })}
    </div>
  );
});
List.defaultProps = {
  staffs: [],
};
export default List;
