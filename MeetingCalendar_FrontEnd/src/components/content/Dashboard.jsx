import React from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import UsersAndPermissions from '../dashboard/UsersAndPermissions';
import Notifications from '../dashboard/Notifications';
import Analytics from '../dashboard/Analytics';
import Settings from '../dashboard/Settings';
import ScheduleMeeting from './ScheduleMeeting';
import ManageMeetings from './ManageMeetings';
import Home from '../navbar/Home';
import { AiOutlineDashboard } from "react-icons/ai";
import { FaCalendarPlus } from "react-icons/fa";
import { FaCalendar } from "react-icons/fa";
import { FaUsers } from "react-icons/fa";
import { IoIosNotifications } from "react-icons/io";
import { BsGraphUpArrow } from "react-icons/bs";
import { IoSettingsSharp } from "react-icons/io5";

const Dashboard = () => {
    const navigate = useNavigate();
    const dashboardItems = [
        {id: 1, name: "Schedule Meeting", icon: <FaCalendarPlus />, navigateUrl: "/dashboard/scheduleMeeting"},
        {id: 2, name: "Manage Meetings", icon: <FaCalendar />, navigateUrl: "/dashboard/manageMeeting"},
        {id: 3, name: "Users & Permissions", icon: <FaUsers />, navigateUrl: "/dashboard/usersAndPermissions"},
        {id: 4, name: "Notifications", icon: <IoIosNotifications />, navigateUrl: "/dashboard/notifications"},
        {id: 5, name: "Analytics", icon: <BsGraphUpArrow />, navigateUrl: "/dashboard/analytics"},
        {id: 6, name: "Settings", icon: <IoSettingsSharp />, navigateUrl: "/dashboard/settings"}
    ];

    const displayDashboardItems = () => {
        let liElements = dashboardItems.map((item) => {
            const liElement = 
                <li className="list-group-item" key={item.id}>
                    <button className="list-group-item list-group-item-action" id={item.id} 
                        onClick={() => handleDashboardClick(item.id, item.navigateUrl)}>{item.icon} {item.name}
                    </button>
                </li>
            return liElement;
        });
        return liElements;
    };

    const handleDashboardClick = (id, navigateUrl) => {
        const appendClassName = "active";
        for(let i=1; i<=dashboardItems.length; i++) {
            document.getElementById(i).classList.remove(appendClassName);
        }
        document.getElementById(id).classList.add(appendClassName);
        navigate(navigateUrl);
    };

    return (
    <div className='row'>
        <div className='col-md-3'>
            <div className='my-2 ms-4'>
                <div className='card'>
                <div className='card-header bg-dark text-light'>
                    <h4 className='text-center'><span><AiOutlineDashboard /></span> Dashboard</h4>
                </div>
                <ul className="list-group list-group-flush inActive">
                    {displayDashboardItems()}
                </ul>
            </div>
            </div>
        </div>
        <div className='col-md-9'>
            <Routes>
                <Route path='/home' element={<Home />} />
                <Route path='/scheduleMeeting' element={<ScheduleMeeting />} />
                <Route path='/manageMeeting' element={<ManageMeetings />} />
                <Route path='/usersAndPermissions' element={<UsersAndPermissions />} />
                <Route path='/notifications' element={<Notifications />} />
                <Route path='/analytics' element={<Analytics />} />
                <Route path='/settings' element={<Settings />} />
            </Routes>
        </div>
    </div>
    );
};

export default Dashboard;