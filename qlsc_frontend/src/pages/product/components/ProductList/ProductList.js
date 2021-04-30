/* eslint-disable no-shadow */
import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import './styles.scss';
import ProductListBody from './ProductListBody/ProductListBody';
import ProductListHeader from './ProductHeader/ProductListHeader';
function ProductList(props) {
  const {  } = props;
  useEffect(() => {
  }, []);
  return (
    <div className="product-screen-wrapper">
      <ProductListHeader />
      <ProductListBody/>
    </div>
  );
}
ProductList.defaultProps = {

};

export default React.memo(connect(null, null)(ProductList));
