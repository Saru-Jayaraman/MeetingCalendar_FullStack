import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { FcCalendar } from "react-icons/fc";
import { FaCircleUser } from "react-icons/fa6";
import { VscSignOut } from "react-icons/vsc";
import { MdOutlineSettings } from "react-icons/md";

const Navbar = () => {
    useEffect(() => {
        fetchAllNavbarDetailsAPICall();
        fetchAllNavbarDropdownAPICall();
    }, []);

    const apiEndpoint = "http://localhost:8080/api/navbar";
    const [navItems, setNavItems] = useState([]);
    const [navDemoDropdownItems, setNavDemoDropdownItems] = useState([]);
    const iconMap = {
        VscSignOut: VscSignOut,
        MdOutlineSettings: MdOutlineSettings
    };
    const IconRender = ({iconName}) => {
        const IconComponent = iconMap[iconName];
        return IconComponent ? <IconComponent /> : null;
    };

    const fetchAllNavbarDetailsAPICall = async () => {
        console.log("Step1: Request FETCH ALL...");
        await axios.get(apiEndpoint)
                    .then(response => {
                        console.log("Step2: Response FETCH ALL...");
                        if(response.status === 200) {
                            console.log(response.data);
                            setNavItems(response.data);
                        } else {
                            console.log("Unexpected response status...", response.status);
                        }
                    })
                    .catch(error => {
                        console.log("Error on fetching navbar details...", error);
        });
        console.log("Step3: Finish FETCH ALL...");
    };
    const fetchAllNavbarDropdownAPICall = async () => {
        const dropdownUrl = apiEndpoint.concat("/dropdown");
        console.log("Step1: Request FETCH ALL...");
        await axios.get(dropdownUrl)
                    .then(response => {
                        console.log("Step2: Response FETCH ALL...");
                        if(response.status === 200) {
                            console.log(response.data);
                            setNavDemoDropdownItems(response.data);
                        } else {
                            console.log("Unexpected response status...", response.status);
                        }
                    })
                    .catch(error => {
                        console.log("Error on fetching navbar dropdown details...", error);
        });
        console.log("Step3: Finish FETCH ALL...");
    };
    return (
    <div className='container-fluid'>
        <nav className="navbar navbar-expand-sm bg-dark" style={{height: "40px"}}>
            <FcCalendar className='mx-2' style={{width: '30px', height: '30px'}}/>
            <ul className='navbar-nav'>
                {
                    navItems.map((item) => {
                        const liItem = 
                            <li className='nav-item' key={item.navbarId}>
                                <Link className='nav-link text-light' to={item.navbarUrl}>{item.navbarName}</Link>
                            </li>
                        return liItem;
                    })
                }
            </ul>
            <div className="ms-auto text-light dropdown">
                <span className='px-1'><FaCircleUser /></span>
                <button className='btn btn-dark mx-5 px-1 dropdown-toggle' type="button" data-bs-toggle="dropdown" id="dropdownMenuButton" aria-expanded="false">Demo</button>
                <ul className="dropdown-menu bg-secondary" aria-labelledby="dropdownMenuButton">
                    {
                        navDemoDropdownItems.map((item) => {
                            const liItem = 
                                <li className='border rounded bg-light m-1' key={item.navbarDropdownId} >
                                    <a className="dropdown-item" href={item.navbarDropdownHref}>
                                        <span><IconRender iconName={item.navbarDropdownIcon}/></span> <b>{item.navbarDropdownName}</b>
                                    </a>
                                </li>
                            return liItem;
                        })
                    }
                </ul>
            </div>
        </nav>
    </div>
    );
};

export default Navbar;