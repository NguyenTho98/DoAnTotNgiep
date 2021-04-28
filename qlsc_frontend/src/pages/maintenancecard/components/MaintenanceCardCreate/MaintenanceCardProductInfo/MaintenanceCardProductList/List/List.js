import { forEach } from 'lodash';
import React from 'react';
import { connect } from 'react-redux';
import * as Icons from 'components/sapo/deliveryCollation/commons/Icons';
import { updateOrderCollation } from '../../../../../actions/deliveryCollation';
import Item from '../Item/Item';

function List(props) {
  const {
    listOrderCollation,
    updateOrderCollation,
  } = props;
  const onChangeItem = (id, name, value) => {
    const tmp = Number(value);
    if (tmp > 100000000000) {
      SapoApp.flashError(
        'Giá không được quá 99,999,999,999'
      );
      const newArr = [...listOrderCollation];
      updateOrderCollation(newArr);
    } else if (tmp < 0) {
      const newArr = [...listOrderCollation];
      updateOrderCollation(newArr);
    } else {
      let check = false;
      const newArr = [...listOrderCollation];
      newArr.forEach(element => {
        if (element.id === id) {
          element[name] = tmp;
          check = true;
        }
      });
      if (check) {
        updateOrderCollation(newArr);
      }
    }
  };
  const removeOrderItem = (id) => {
    const arr = listOrderCollation.filter((item) => item.id !== id);
    updateOrderCollation(arr);
  };
  if (listOrderCollation.length === 0) {
    return (
      <div className="is-empty-order">
        <Icons.IconIsEmptyOrder />
        <p>Phiếu đối soát của bạn chưa có đơn hàng nào</p>
      </div>
    );
  }
  return (
    <React.Fragment>
      <div className="list-order-selected">
        {
        listOrderCollation.map((item, index) => (
          <Item
            key={index}
            item={item}
            onChangeItem={(id, name, value) => onChangeItem(id, name, value)}
            removeOrderItem={(id) => removeOrderItem(id)}
          />
        ))
      }
      </div>
    </React.Fragment>
  );
}
const mapStateToProps = (state) => {
  const { deliveryCollation: { orderCollations: { items, listOrderCollation } } } = state;
  return {
    items,
    listOrderCollation,
  };
};

const mapDispatchToProps = (dispatch) => ({
  updateOrderCollation: (list) => dispatch(updateOrderCollation(list)),
});
export default connect(mapStateToProps, mapDispatchToProps)(List);
