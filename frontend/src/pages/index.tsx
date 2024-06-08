import AppContext from '@/components/app/AppContext';
import QuotePage from '@/components/pages/QuotePage/QuotePage';
import EnvironmentVariables from '@/config/EnvironmentVariables';
import loadEnvironmentVariables from '@/config/loadEnvironmentVariables';
import React from 'react';

type Props = {
    env: EnvironmentVariables;
};

const IndexPage: React.FC<Props> = ({ env }) => {

    return (
        <AppContext env={env}>
            <QuotePage />
        </AppContext>
    );
};

export const getStaticProps = async () =>
    ({ props: { env: loadEnvironmentVariables() } });

export default IndexPage;
