import {AuthProvider} from "./auth";

// @ts-ignore
export const AppProvider = ({children}) => (
    <>
        <AuthProvider>{children}</AuthProvider>
    </>
);