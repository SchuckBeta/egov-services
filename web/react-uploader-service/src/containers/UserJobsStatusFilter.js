import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import CheckboxUi from "../atomic-components/CheckboxUi";
import { applyJobsStatusFilter } from "../actions/filter";

class UserJobsStatusFilterContainer extends Component {
  static propTypes = {
    applyJobsStatusFilter: PropTypes.func.isRequired
  };
  state = {
    checkedValues: []
  };

  options = [
    {
      value: "completed",
      label: "Completed"
    },
    {
      value: "in-progress",
      label: "In Progress"
    },
    {
      value: "new",
      label: "New"
    },
    {
      value: "failed",
      label: "Failed"
    }
  ];

  onChecked = (e, value) => {
    const { checkedValues } = this.state;
    let jobStatuses;
    if (checkedValues.indexOf(value) !== -1) {
      jobStatuses = checkedValues.filter(
        checkedValue => checkedValue !== value
      );
    } else {
      jobStatuses = checkedValues.concat(value);
    }
    this.props.applyJobsStatusFilter(jobStatuses);
    this.setState({ checkedValues: jobStatuses });
  };

  render() {
    const { options, onChecked } = this;

    return (
      <div>
        <h5>By Status</h5>
        <CheckboxUi options={options} onCheck={onChecked} />
      </div>
    );
  }
}

const mapDispatchToProps = dispatch => ({
  applyJobsStatusFilter: jobStatus => dispatch(applyJobsStatusFilter(jobStatus))
});

export default connect(null, mapDispatchToProps)(UserJobsStatusFilterContainer);