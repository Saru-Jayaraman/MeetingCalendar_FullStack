import React from 'react';

const Home = () => {
    return (
    <div className='container-fluid row'>
        <div className='my-2 text-center' style={{background: "#48d1cc"}}>
            <h2 style={{background: "#c9ffe5"}}>Business Meeting Calendar</h2>
            <h4 className='fw-light'>Designed for seamless organization, perfect balance of functionality and professionalism for your business needs.</h4>
            <img src='\src\assets\MeetingPlanner.png' alt="Meeting Planner" style={{width: "600px", height: "400px"}} />
        </div>
    </div>
    );
};

export default Home;