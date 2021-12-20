import React from "react";

const Loading = () => {
  return (
    <div className="min-h-full pt-16 pb-12 flex flex-col bg-white">
      <main className="flex-grow flex flex-col justify-center max-w-7xl w-full mx-auto px-4 sm:px-6 lg:px-8">
        <div className="py-16">
          <div className="text-center">
            <p className="mt-2 text-base text-gray-500">Loading...</p>
          </div>
        </div>
      </main>
    </div>
  );
};

Loading.propTypes = {};

Loading.defaultProps = {};

export default Loading;
