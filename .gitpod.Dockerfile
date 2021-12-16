FROM gitpod/workspace-full:latest
RUN bash -c ". .nvm/nvm.sh && nvm install 14.18.2 && nvm use 14.18.2 && nvm alias default 14.18.2"
RUN echo "nvm use default &>/dev/null" >> ~/.bashrc.d/51-nvm-fix