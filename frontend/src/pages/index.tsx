import App from '@/app/App';
import QuotePage from '@/components/pages/QuotePage/QuotePage';
import EnvironmentVariables from '@/config/EnvironmentVariables';
import loadEnvironmentVariables from '@/config/loadEnvironmentVariables';
import React from 'react';

type Props = {
    env: EnvironmentVariables;
};

const IndexPage: React.FC<Props> = ({ env }) => (
    <App env={env}>
        <QuotePage />
    </App>
);

export const getStaticProps = async () =>
    ({ props: { env: loadEnvironmentVariables() } });

export default IndexPage;
