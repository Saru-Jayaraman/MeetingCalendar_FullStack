import React from 'react';

const Services = () => {
    return (
    <div className='container-fluid my-5 mx-5'>
        <h3>Meeting Scheduling Tools</h3>
        <p>This website helps user to plan and organize meetings by finding suitable times for participants.</p>
        <p>Maximum of 5 participants can be added for scheduling meeting.</p>
        <ul style={{width: "600px"}}><h5>Customers can invoke the following Services through this platform:</h5>
            <hr />
            <li><span className='bg-success'>Create</span> meeting</li>
            <hr />
            <li><span className='bg-warning'>Update</span> meeting</li>
            <hr />
            <li><span className='bg-danger'>Cancel</span> meeting</li>
            <hr />
        </ul>
    </div>
    );
};

export default Services;