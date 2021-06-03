import React from 'react';
import { useHistory } from 'react-router';

import Chart from './Chart';

const dataPro = [
  {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  },
  {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  },
  {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }, {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }
  , {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }
  , {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }
  , {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }
  , {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }, {
    "cancelled_order_number": 0,
    "cancelled_quantity": 0,
    "cancelled_rate": 0,
    "connection_id": 601,
    "dim_variation_id": 67462,
    "image": "",
    "item_id": 1253851778,
    "link": null,
    "mapping": false,
    "order_number": 2,
    "quantity": 8,
    "revenue": 11398500,
    "sapo_product_id": 0,
    "sapo_sku": "",
    "sapo_variant_id": 0,
    "variation_id": "4677287841",
    "variation_name": "Restricted for Test  HomeBase FURDINI Tủ gỗ 3 ngăn Thái Lan W64xD36xH82CM màu trắng/gỗ sồi"
  }
]

/**
 * view:
 * 1: Giá trị bán cao nhất
 * 2: Lượng bán nhiều nhất
 * 3: Tỷ lệ hủy cao
 * 4: Số lượng hủy cao
 * */

function ReportProduct(props) {
  // const { data, fetching } = props;
  // const [view, setView] = useState(1);
  const history = useHistory();
  // const { search } = history.location;
  // const searchParams = new URLSearchParams(search);
  // const sortType = searchParams.get('sort-type');
  // const sortField = searchParams.get('sort-field');

  // const onChange = (sortField, sortType) => {
  //   const newParams = getNewParams(history.location.search, 'sort-type', sortType);
  //   const lastParams = getNewParams(newParams, 'sort-field', sortField);
  //   pushstate(history, `/home/report?${lastParams}`);
  // };

  // const getType = () => {
  //   if (sortType === 'up' && sortField === 'revenue') {
  //     return 1;
  //   }
  //   if (sortType === 'up' && sortField === 'quantity') {
  //     return 2;
  //   }
  //   if (sortType === 'up' && sortField === 'cancelled_rate') {
  //     return 3;
  //   }
  //   if (sortType === 'up' && sortField === 'cancelled_quantity') {
  //     return 4;
  //   }
  //   return 1;
  // };

  // if (fetching) {
  //   return (
  //     <div className="report-product-content" style={{ height: 483 }}>
  //       <div className="d-flex align-items-center position-relative report-product-header">
  //         <div className="font-weight-bold title">
  //           Top 10 sản phẩm
  //         </div>
  //       </div>
  //       <div className="d-flex align-items-center justify-content-center" style={{ height: 410 }}>
  //         <ChartLoading />
  //       </div>
  //     </div>
  //   );
  // }

  // if (!data.length) {
  //   return (
  //     <div className="report-product-content">
  //       <ChartEmpty />
  //     </div>
  //   )
  // }

  return (
    <React.Fragment>
      <div className="report-product-content">
        <div className="d-flex align-items-center position-relative report-product-header">
          <div className="font-weight-bold title">
            Top 10 sản phẩm
          </div>
        </div>
        <Chart
          type={1}
          data={dataPro}
        />
      </div>
    </React.Fragment>
  );
}

export default ReportProduct;
