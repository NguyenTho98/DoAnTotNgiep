import React from 'react';

function Address(props) {
  
  return (
    <span className="filter-feed-comment-detail">
            <span className="active-status">
              {this.state.filterCommentAddress.commentHasAddress && this.state.filterCommentAddress.status ? (
                <button className="btn-active" onClick={() => this.filterAction(option, true)} style={{ background: '#4e95e3', color: 'white' }} type="button">
                  Có địa chỉ
                </button>
              ) : (
                <button className="btn-active" onClick={() => this.filterAction(option, true)} type="button">
                  Có địa chỉ
                </button>
              )}
            </span>
            <span className="active-status">
              {!this.state.filterCommentAddress.commentHasAddress && this.state.filterCommentAddress.status ? (
                <button className="btn-unactive" onClick={() => this.filterAction(option, false)} style={{ background: '#4e95e3', color: 'white' }} type="button">
                  Không có địa chỉ
                </button>
              ) : (
                <button className="btn-unactive" onClick={() => this.filterAction(option, false)} type="button">
                  Không có địa chỉ
                </button>
              )}
            </span>
          </span>
  );
}

export default Address;
