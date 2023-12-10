import {createContext, useContext, useMemo} from 'react';
import {useCookies} from 'react-cookie';
import {useNavigate} from 'react-router-dom';
import {LoginService} from "./service/LoginService";

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const navigate = useNavigate();
    const [cookies, setCookies] = useCookies();

    const login = async (username, password) => {
        LoginService.auth({
            username: username,
            password: password
        }).then(r => {
            setCookies('token', r.token);
            navigate("/");
        })

    };

    const value = useMemo(
        () => ({
            cookies,
            login,
        }),
        [cookies]
    );

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    )
};

export const useAuth = () => {
    return useContext(AuthContext)
};
