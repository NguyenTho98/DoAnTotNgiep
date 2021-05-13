import React from 'react';

const url = '/images/report-empty.png';
class ChartEmpty extends React.Component {
  render() {
    const { text } = this.props;
    return (
      <div className="content_body__bottom_empty">
        <div className="number_empty">
          <img src={url} alt="chartEmpty" />
          <div className="txt_empty">{text}</div>
        </div>
      </div>
    );
  }
}

ChartEmpty.defaultProps = {
};

export default ChartEmpty;
