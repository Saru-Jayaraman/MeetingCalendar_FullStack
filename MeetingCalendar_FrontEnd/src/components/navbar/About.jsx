import React from 'react';

const About = () => {
    return (
    <div className='container-fluid row my-5'>
        <div className='col-md-6 my-5 py-5 mx-1'>
            <h2>A Simple Business Meeting Calendar</h2>
            <p>Meeting Calendar platform offers Individual/Enterprise for scheduling meeting and customers can manage them through updating and cancelling.</p>
            <p>Our platform was built from the ground up to be a powerful, integrated, and easy-to-use system that leads to better customer experiences and business growth.</p>
        </div>
        <div className='col-md-6 my-5' style={{width: "600px"}}>
            <img src='\src\assets\MeetingPlanner.png' alt="Meeting Planner" style={{width: "600px", height: "300px"}} />
        </div>
    </div>
    );
};

export default About;