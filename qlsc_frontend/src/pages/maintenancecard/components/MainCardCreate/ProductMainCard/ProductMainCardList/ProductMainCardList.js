import React from 'react';
import Header from './Header/Header';
import List from './List/List';
import Total from './Total/Total';

import './styles.scss';
import Search from './Search/Search';

function ProductMainCardList(props) {
  return (
    <div className="delivery-collation-order-list-wrapper">
      <Search/>
      <Header />
      <List />
      <Total/>
    </div>
  );
}

export default ProductMainCardList;
