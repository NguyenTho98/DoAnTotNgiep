import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import TitleAndAction from "./TitleAndAction/TitleAndAction";
import "./styles.scss";
import Accessories from "./Accessories/Accessories";
import Service from "./Service/Service";
import InfoProductFooter from "./InfoProductFooter/InfoProductFooter";
import { upLoadImage, saveProductService } from "../../actions/ProductAction";
import pushstate from "utils/pushstate";

const initialState = {
  name: null,
  code: null,
  quantity: null,
  unit: null,
  pricePerUnit: null,
  description: null,
  images: [],
  type: null,
};
function ProductCreate(props) {
  const { onUpLoadImage, onSaveProductService } = props;
  const [product, setProduct] = useState(initialState);
  const [showContent, setShowContent] = useState(1);
  useEffect(() => {
    onchangeValue("type", 1);
  }, []);

  useEffect(() => {
    onchangeValue("type", showContent);
  }, [showContent]);

  const onchangeValue = (type, value) => {
    setProduct({
      ...product,
      [type]: value,
    });
  };

  const saveProductService = () => {
    onSaveProductService(product).then((json) => {
      if (json && json.success) {
        setProduct(initialState);
        setShowContent(1);
        pushstate(props.history, "/products");
      }
    });
  };

  const cancel = () => {
    setUser(initialState);
    setShowContent(1);
    pushstate(props.history, "/products");
  };

  const handleUploadImage = (file) => {
    onUpLoadImage(file)
      .then((json) => {
        if (json && json.data) {
          const images = [...product.images, json.data];
          onchangeValue("images", images);
        }
      })
      .catch((e) => {
        console.error(e);
        return e;
      });
  };

  const handleChange = () => {
    if (showContent === 1) {
      setShowContent(2);
    } else {
      setShowContent(1);
    }
  };

  const renderContent = () => {
    if (showContent === 1) {
      return (
        <Accessories
          product={product}
          onchangeValue={onchangeValue}
          handleUploadImage={handleUploadImage}
        />
      );
    }
    if (showContent === 2) {
      return <Service product={product} onchangeValue={onchangeValue} />;
    }
  };

  return (
    <React.Fragment>
      <div className="product-screen-wrapper-create">
        <TitleAndAction setShowContent={() => handleChange()} />
        <div className="row">
          {renderContent()}
          <InfoProductFooter
            saveProductService={saveProductService}
            cancel={cancel}
          />
        </div>
      </div>
    </React.Fragment>
  );
}
ProductCreate.defaultProps = {};

const mapDispatchToProps = (dispatch) => ({
  onUpLoadImage: (file) => dispatch(upLoadImage(file)),
  onSaveProductService: (product) => dispatch(saveProductService(product)),
});

export default connect(null, mapDispatchToProps)(ProductCreate);
