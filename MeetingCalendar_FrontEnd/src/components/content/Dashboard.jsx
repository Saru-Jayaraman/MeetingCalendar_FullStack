import React, { useEffect, useState } from 'react';
import axios from 'axios';
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
    useEffect(() => {
        fetchAllDashboardDetailsAPICall();
    }, []);

    const apiEndpoint = "http://localhost:8080/api/dashboard";
    const navigate = useNavigate();
    const [dashboardItems, setDashboardItems] = useState([]);
    const iconMap = {
        FaCalendarPlus: FaCalendarPlus,
        FaCalendar: FaCalendar,
        FaUsers: FaUsers,
        IoIosNotifications: IoIosNotifications,
        BsGraphUpArrow: BsGraphUpArrow,
        IoSettingsSharp: IoSettingsSharp
    };

    const IconRender = ({iconName}) => {
        const IconComponent = iconMap[iconName];
        return IconComponent ? <IconComponent /> : null;
    };

    const fetchAllDashboardDetailsAPICall = async () => {
        console.log("Step1: Request FETCH ALL...");
        await axios.get(apiEndpoint)
                    .then(response => {
                        console.log("Step2: Response FETCH ALL...");
                        if(response.status === 200) {
                            console.log(response.data);
                            setDashboardItems(response.data);
                        } else {
                            console.log("Unexpected response status...", response.status);
                        }
                    })
                    .catch(error => {
                        console.log("Error on fetching dashboard details...", error);
        });
        console.log("Step3: Finish FETCH ALL...");
    };

    const displayDashboardItems = () => {
        let liElements = dashboardItems.map((item) => {
            const liElement = 
                <li className="list-group-item" key={item.dashboardTabId}>
                    <button className="list-group-item list-group-item-action" id={item.dashboardTabId} 
                        onClick={() => handleDashboardClick(item.dashboardTabId, item.dashboardTabNavigateUrl)}>
                            <IconRender iconName={item.dashboardTabIcon} /> {item.dashboardTabName}
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