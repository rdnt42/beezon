import {createContext, useContext, useMemo} from 'react';
import {useCookies} from 'react-cookie';
import {useNavigate} from 'react-router-dom';
import {LoginService} from "./service/LoginService";
import {jwtDecode} from "jwt-decode";

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

    const isValidToken = () => {
        if (!cookies.token) return false;

        let payload = jwtDecode(cookies.token);

        if (!payload || !payload.exp || payload.exp < (Date.now() / 1000)) {
            localStorage.clear();
            return false;
        }

        return true;
    };

    const value = useMemo(
        () => ({
            cookies,
            login,
            isValidToken,
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
