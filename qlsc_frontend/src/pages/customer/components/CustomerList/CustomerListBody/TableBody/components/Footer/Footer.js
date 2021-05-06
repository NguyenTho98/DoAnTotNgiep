import React, { useState, useEffect } from "react";
import Pagination from "components/Pagination/Pagination";
import { connect } from "react-redux";
import "../../styles/footer.scss";

function Footer(props) {
  const { customer } = props;
  const { currentPage, totalItems, totalPages, customers } = customer;
  const [size, setSize] = useState(0);

  useEffect(() => {
    if (customers && customers.length) setSize(customers.length);
  }, [customer]);

  const calculateBegin = () => {
    if (currentPage === 1) {
      return 1;
    }
    return size * currentPage;
  };

  const calculateEnd = () => {
    if (totalPages <= 1) {
      return totalItems;
    }
    return currentPage * size;
  };

  const onChangePage = (id) => {
    const { onClick, resetSelected } = props;
    onClick(id);
    resetSelected();
  };

  const { total, page, fetching, isEmpty } = props;
  if (fetching || isEmpty) return null;
  return (
    <div className="d-flex delivery-collations-footer">
      <div className="result-info">
        Hiển thị kết quả từ&nbsp;
        {calculateBegin()} -&nbsp;
        {calculateEnd()} trên tổng {totalItems}
      </div>
      <div className="margin-left-auto" />
      <div className="products-pagination">
        <Pagination
          total={total}
          page={page}
          size={size}
          onClick={onChangePage}
        />
      </div>
    </div>
  );
}

Footer.defaultProps = {
  size: 20,
};

const mapStateToProps = (state) => {
  const {
    mainCards: {
      mainCard: { total, page },
      ui: { fetching },
    },
  } = state;
  return {
    total,
    page,
    fetching,
  };
};

const mapDispatchToProps = (dispatch) => ({
  onClick: (page) => dispatch(fetchMainCard(null, page)),
});

export default connect(mapStateToProps, mapDispatchToProps)(Footer);
