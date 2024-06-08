import loadEnvironmentVariables from '@/config';
import EnvironmentVariables from '@/config/EnvironmentVariables';
import { GetStaticProps } from 'next';
import React from 'react';

type Props = {
    env: EnvironmentVariables;
};

const IndexPage: React.FC<Props> = ({ env }: Props) => {
    return (
        <div>
            <h1>Gimme Quote</h1>
            <p>TODO: a quote should appear here</p>
            <div>
                {env.QUOTE_SERVICE_HOST}, {env.QUOTE_SERVICE_PORT}
            </div>
        </div>
    );
};

export const getStaticProps: GetStaticProps<Props> = async () => {
    return {
        props: {
            env: loadEnvironmentVariables()
        }
    }
};

export default IndexPage;
