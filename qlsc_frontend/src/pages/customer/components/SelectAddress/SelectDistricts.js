import React from 'react';
import { connect } from 'react-redux';
import Select2 from 'react-select2-wrapper';
import { getWard } from '../../actions/locationActions';

class SelectDistricts extends React.Component {
  
//   componentDidMount() {
//     const {
//       city, listAddress, getWard, illusory,
//     } = this.props;
//     const cities = Object.values(listAddress);
//     const defaultValue = cities.find(
//       address => address.district_id !== '0'
//         && city === address.city_name
//         && illusory === address.district_name,
//     );
//     if (defaultValue && defaultValue.city_id && defaultValue.district_id) {
//       getWard(defaultValue.city_id, defaultValue.district_id, defaultValue.country_id);
//     }
//   }

//   UNSAFE_componentWillReceiveProps(nextProps) {
//     const {
//       city, listAddress, getWard, illusory,
//     } = this.props;
//     if (city !== nextProps.city || illusory !== nextProps.illusory) {
//       const cities = Object.values(listAddress);
//       const defaultValue = cities.find(
//         address => address.district_id !== '0'
//             && nextProps.city === address.city_name
//             && nextProps.illusory === address.district_name,
//       ) || {};
//       if (defaultValue && defaultValue.city_id && defaultValue.district_id) {
//         getWard(defaultValue.city_id, defaultValue.district_id, defaultValue.country_id);
//       }
//     }
//   }

  getCityDistrict(){
    const { listAddress } = this.props;
    return Object.values(listAddress);
  };

  render() {
    const { city, illusory, listAddress } = this.props;
    const cities = Object.values(listAddress);
    const defaultValue = cities
      ? cities.find(
        address => address.district_id !== '0'
            && address.district_id !== 0
            && city === address.city_name
            && illusory === address.district_name,
      ) : {};

    return (
      <Select2
        defaultValue={defaultValue ? defaultValue.id : ''}
        data={cities}
        onSelect={this.props.onSelect}
        options={{
          placeholder: 'Tỉnh/Thành phố - Quận/Huyện',
        }}
      />
    );
  }
}

const mapStateToProps = state => {
  const {
    locations: { city },
  } = state;
  return {
    listAddress: city,
  };
};

export default connect(
  mapStateToProps,
  { getWard },
  null,
  { withRef: true },
)(SelectDistricts);
