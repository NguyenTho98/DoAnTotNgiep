import React from 'react';
import Phone from "./Options/Phone";
import Address from "./Options/Address";
import Time from "./Options/Time";

function Options(props) {
  const {
    option,
    filterPhone,
    filterAddress,
    filterAction,
  } = props;

  if (option === 'phone') {
    return (<Phone option={option} filterPhone={filterPhone} filterAction={filterAction} />);
  } else if (option === 'address') {
    return (<Address option={option} filterAddress={filterAddress} ilterAction={filterAction} />);
  } else if (option === 'time') {
    return (<Time />);
  }
  return null;
}

export default Options;
